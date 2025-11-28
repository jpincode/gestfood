package com.gestfood.gestfood.business.service;

import java.util.List;

public interface InnerDefaultCrud<RequestEntity, ResponseEntity, UpdateEntity> {
    void create(RequestEntity requestEntity);
    List<ResponseEntity> read();
    ResponseEntity read(Long id);
    void update(Long id, UpdateEntity updateEntity);
    void delete(Long id);
}
