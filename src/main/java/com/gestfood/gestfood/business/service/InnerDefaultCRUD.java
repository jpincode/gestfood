package com.gestfood.gestfood.business.service;

import java.util.List;

public interface InnerDefaultCRUD<Entity> {
    void create(Entity entity);
    List<Entity> read();
    Entity read(Long id);
    void update(Entity entity);
    void delete(Long id);
}