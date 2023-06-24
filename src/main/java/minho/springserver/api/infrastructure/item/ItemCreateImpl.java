package minho.springserver.api.infrastructure.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.ItemCreate;
import minho.springserver.api.domain.item.entity.Items;
import org.springframework.stereotype.Component;

import java.util.Optional;

// CreateImpl -> insert/update/delete로 정의
@Component
@RequiredArgsConstructor
public class ItemCreateImpl implements ItemCreate {
    private final ItemsRepository itemsRepository;

//    @Override
    public Long insertItem(Items items) {
        Items savedItem = this.itemsRepository.save(items);
        Long savedId = savedItem.getId();
        System.out.println(savedId);
        return savedId;
    }

//    @Override
//    public Optional<Long> disabledSeller(Long sellerId) {
//        Optional<Sellers> seller = this.sellersRepository.findById(sellerId);
//        if (seller.isEmpty()) return Optional.ofNullable(null);
//        seller.get().disable();
//        return Optional.of(sellerId);
//    }
}
