package main


import (
	"fmt"
	"sync"
	"time"
)

func checkingParking(parking *[]bool) int {
	for i := range *parking {
		if !((*parking)[i]) {
			(*parking)[i] = true
			return i
		}
	}
	return -1
}

func performParking(component int, parking *[]bool, readSemaphore, writeSemaphore chan bool, waitGroup *sync.WaitGroup) {
	defer waitGroup.Done()
	for {
		readSemaphore <- true
		fmt.Println("Checking parking # ", component, "...")

		if (checkingParking(parking) != -1) {
			writeSemaphore <- true
		} else {
			<-readSemaphore
			time.Sleep(time.Second)
		}
	}
}

func main() {
	const parkingNum = 3
	const carsNum = 5;
	var waitGroup sync.WaitGroup
	var parking = make([]bool, parkingNum)

	var readSemaphore = make(chan bool)
	var writeSemaphore = make(chan bool, 1)

	for i := 0; i < carsNum; i++ {
		waitGroup.Add(1)
		go performParking(i, &parking, readSemaphore, writeSemaphore, &waitGroup)
	}
	waitGroup.Wait()
}
