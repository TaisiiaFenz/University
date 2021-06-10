package edu.coursera.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public final class ReciprocalArraySum {

    private ReciprocalArraySum() {
    }

    protected static double seqArraySum(final double[] input) {
        double sum = 0;

        // Compute sum of reciprocals of array elements
        for (int i = 0; i < input.length; i++) {
            sum += 1 / input[i];
        }

        return sum;
    }

    private static int getChunkSize(final int nChunks, final int nElements) {
        // Integer ceil
        return (nElements + nChunks - 1) / nChunks;
    }

    private static int getChunkStartInclusive(final int chunk,
            final int nChunks, final int nElements) {
        final int chunkSize = getChunkSize(nChunks, nElements);
        return chunk * chunkSize;
    }

    private static int getChunkEndExclusive(final int chunk, final int nChunks,
            final int nElements) {
        final int chunkSize = getChunkSize(nChunks, nElements);
        final int end = (chunk + 1) * chunkSize;
        if (end > nElements) {
            return nElements;
        } else {
            return end;
        }
    }

    /**
     * This class stub can be filled in to implement the body of each task
     * created to perform reciprocal array sum in parallel.
     */
    private static class ReciprocalArraySumTask extends RecursiveAction {
      
    	static final int SEQUENTIAL_THREASHOLD = 50000;
    	/**
         * Starting index for traversal done by this task.
         */
        private final int startIndexInclusive;
        /**
         * Ending index for traversal done by this task.
         */
        private final int endIndexExclusive;
        /**
         * Input array to reciprocal sum.
         */
        private final double[] input;
        /**
         * Intermediate value produced by this task.
         */
        private double value;

        ReciprocalArraySumTask(final int setStartIndexInclusive,
                final int setEndIndexExclusive, final double[] setInput) {
            this.startIndexInclusive = setStartIndexInclusive;
            this.endIndexExclusive = setEndIndexExclusive;
            this.input = setInput;
        }

        public double getValue() {
            return value;
        }

        @Override
        protected void compute() {
        	for(int i = startIndexInclusive; i < endIndexExclusive; i++)
        	{
        		value += 1/input[i];
        	}
        }
    }

    protected static double parArraySum(final double[] input) {
        assert input.length % 2 == 0;
        
        int midIndex = input.length/2;
        
        ReciprocalArraySumTask leftHalfTask = new ReciprocalArraySumTask(0, midIndex , input);
        ReciprocalArraySumTask rightHalfTask = new ReciprocalArraySumTask(midIndex, input.length , input);
        ForkJoinTask.invokeAll(leftHalfTask, rightHalfTask);

         return leftHalfTask.getValue() + rightHalfTask.getValue();
        
    }

    protected static double parManyTaskArraySum(final double[] input,
            final int numTasks) {
        double sum = 0;

        List<ReciprocalArraySumTask> subTasksList = createSubTask(input, numTasks);  
        
        RecursiveAction.invokeAll(subTasksList);
        
        for(ReciprocalArraySumTask subTask : subTasksList)
        {
        	subTask.join();
        	sum += subTask.getValue();
        }
        return sum;
    }
    
    private static List<ReciprocalArraySumTask> createSubTask(double[] input, int numTasks)
    {
    	  int taskNum = numTasks;
    	  
         if(taskNum>input.length)
         {
             taskNum = input.length;
         }
    	List<ReciprocalArraySumTask> subTaskList = new ArrayList<>();
    	for(int i = 0; i < numTasks; i++)
    	{ 
    		ReciprocalArraySumTask subtask = new ReciprocalArraySumTask(getChunkStartInclusive(i, taskNum, input.length), getChunkEndExclusive(i, taskNum, input.length), input);
    		subTaskList.add(subtask);
    	}
    	return subTaskList;
    }
}
