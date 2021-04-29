package RMI;

import models.Catalog;

import java.io.File;
import java.rmi.Remote;
import java.util.List;

public interface RMIInterface extends Remote {

    List<Catalog> getAllCategories();

    List<models.File> getAllFiles();

    List<Catalog> createCategory(String name, String catalog);

    List<File> createFile(String name, String catalog, int size);

    boolean deleteCategoryById(int Id);

    boolean deleteFileById(int Id);
}
