package store.ojuara.produtoapi.service.tenis;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.mapper.ChuteiraMapper;
import store.ojuara.produtoapi.mapper.TenisMapper;
import store.ojuara.produtoapi.repository.ChuteiraRepository;
import store.ojuara.produtoapi.repository.TenisRepository;
import store.ojuara.produtoapi.repository.specification.ChuteiraSpecification;
import store.ojuara.produtoapi.repository.specification.TenisSpecification;
import store.ojuara.produtoapi.service.chuteira.ChuteiraValidator;

@Service
@Transactional
@RequiredArgsConstructor
public class TenisServiceImpl {

    private final TenisRepository repository;
    private final TenisMapper mapper;
    private final TenisValidator validator;
    private final ModelMapper modelMapper;
    private final TenisSpecification specification;
}
