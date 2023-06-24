package minho.springserver.api.domain.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.input.ItemCommand;
import minho.springserver.api.domain.seller.SellerCreate;
import minho.springserver.api.domain.seller.SellerRead;
import minho.springserver.api.domain.seller.entity.Sellers;
import minho.springserver.api.domain.seller.input.SellerCommand;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemCreate itemCreate;
    private final ItemRead itemRead;

    public Long createItem(ItemCommand.CreateItemCommand command) {
        // seller entity 초기화 (command -> entity)
        Sellers initialSeller = Sellers.init(command);

        // seller 저장
        Long insertedId = this.sellerCreate.insertSeller(initialSeller);

        return insertedId;
    }


}
