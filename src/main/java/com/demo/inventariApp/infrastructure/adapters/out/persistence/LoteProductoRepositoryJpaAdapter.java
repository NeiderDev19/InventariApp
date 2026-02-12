package com.demo.inventariApp.infrastructure.adapters.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.demo.inventariApp.application.port.in.LoteProductoInputPort;
import com.demo.inventariApp.application.port.out.LoteProductoRepositoryPort;
import com.demo.inventariApp.domain.model.LoteProducto;
import com.demo.inventariApp.infrastructure.adapters.out.entity.LoteProductoEntity;
import com.demo.inventariApp.infrastructure.adapters.out.entity.ProductoEntity;
import com.demo.inventariApp.infrastructure.adapters.out.repository.LoteProductoJpaRepository;
import com.demo.inventariApp.infrastructure.adapters.out.repository.ProductoJpaRepository;
import com.demo.inventariApp.infrastructure.mapper.LoteProductoMapper;
import com.demo.inventariApp.infrastructure.mapper.ProductoMapper;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class LoteProductoRepositoryJpaAdapter implements LoteProductoRepositoryPort {

    private final LoteProductoJpaRepository loteProductoJpaRepository;

    private final LoteProductoMapper mapper;

    @Override
    public LoteProducto save(LoteProducto loteProducto) {
        LoteProductoEntity newLote = mapper.domainToEntity(loteProducto);
        LoteProductoEntity save = loteProductoJpaRepository.save(newLote);
        return mapper.EntityToDomain(save);
    }

    @Override
    public LoteProducto getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<LoteProducto> getByIdProducto(Long id) {
        return mapper.entityListToDomainList(loteProductoJpaRepository.findByProductoIdProductoOrderByFechaCompraAsc(id));
    }

}
