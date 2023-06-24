package minho.springserver.api.interfaces.item;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.domain.item.input.ItemCommand;
import minho.springserver.response.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/items")
@RestController
public class ItemController {

    @PostMapping
    public ApiResponse<ItemDto.CreateItem.Data> postItem(@Validated @RequestBody ItemDto.CreateItem.RequestBody requestBody) {
        // command 만들기
        ItemCommand.CreateItemCommand command = new ItemCommand.CreateItemCommand(requestBody);

        // interface -> applicatoin
        Long createdId = this.itemApplication.createItem(command);

        // dto 만들기
        ItemDto.CreateItem.Data data = new ItemDto.CreateItem.Data(createdId);

        // 응답 만들기
        return ApiResponse.success(data, "item created");
    }


}
