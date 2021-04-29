package RMI;

import dao.CatalogDaoImpl;
import dao.Connector;
import dao.FileDaoImpl;
import models.Catalog;
import models.File;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ServerRMI {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(8080);
            RMIInterface service = (RMIInterface) new Service();
            registry.rebind("file-system", service);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    static class Service extends UnicastRemoteObject implements RMIInterface {
        CatalogDaoImpl catalogRepository = new CatalogDaoImpl(Connector.getConnection());
        FileDaoImpl fileRepository = new FileDaoImpl(Connector.getConnection());

        Service() throws RemoteException {
            super();
        }

        @Override
        public List<Catalog> getAllCategories() {
            return catalogRepository.findAll();
        }

        @Override
        public List<File> getAllFiles() {
            return fileRepository.findAll();
        }

        @Override
        public List<Catalog> createCategory(String name, String catalog) {
            return (List<Catalog>) catalogRepository.save(new Catalog(name, catalog));
        }

        @Override
        public List<java.io.File> createFile(String name, String catalog, int size) {
            return (List<java.io.File>) fileRepository.save(new File(name, catalog, size));
        }

        @Override
        public boolean deleteCategoryById(int Id) {
            return catalogRepository.deleteById(Id);
        }

        @Override
        public boolean deleteFileById(int Id) {
            return fileRepository.deleteById(Id);
        }
    }
}

