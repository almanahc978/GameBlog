package org.kamil.test.unit.service;

public interface IGameServiceCrud {
    void config();
    void getById();
    void getByIdWithException();
    void getAll();
    void getAllWithException();
    void getByName();
    void getByNameWithException();
    void add();
    void addWithException();
    void update();
    void delete();
}
