package minho.springserver.api.interfaces.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.application.seller.SellerApplication;
import minho.springserver.api.domain.seller.SellerInfo;
import minho.springserver.api.domain.seller.input.SellerCommand;
import minho.springserver.api.domain.seller.input.SellerQuery;
import minho.springserver.exception.BadRequestException;
import minho.springserver.response.ApiResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

//@Slf4j
//@Validated
@Transactional
@RequiredArgsConstructor // private final variable 기반의 constructor를 만듭니다.
@RequestMapping(value = "/api/sellers")
@RestController
public class SellerController {
    private final SellerApplication sellerApplication;

    @PostMapping
    public ApiResponse<SellerDto.CreateSeller.Data> postSeller(@Validated @RequestBody SellerDto.CreateSeller.RequestBody requestBody) {
        // command 만들기
        SellerCommand.CreateSellerCommand command = new SellerCommand.CreateSellerCommand(requestBody);

        // interface -> application
        Long createdId = this.sellerApplication.createSeller(command);

        // dto 만들기
        SellerDto.CreateSeller.Data data = new SellerDto.CreateSeller.Data(createdId);

        // 응답 만들기
        return ApiResponse.success(data, "seller created");
    }

    @GetMapping(value = "/{sellerId}")
    public ApiResponse<SellerDto.ReadSeller.Data> getSeller(@PathVariable("sellerId") Long sellerId) throws BadRequestException {
        // query 만들기
        SellerQuery.ReadSellerQuery query = new SellerQuery.ReadSellerQuery(sellerId);

        // interface -> application
        SellerInfo.Seller seller = this.sellerApplication.readSeller(query);

        // dto 만들기
        SellerDto.ReadSeller.Data data = new SellerDto.ReadSeller.Data(seller);

        // 응답 만들기
        return ApiResponse.success(data);
    }

    @PatchMapping(value = "/{sellerId}")
    public ApiResponse<SellerDto.ModifySellerDisabled.Data> patchSellerDisabled(@PathVariable("sellerId") Long sellerId) throws BadRequestException {
        // command 만들기
        SellerCommand.ModifySellerDisabledCommand command = new SellerCommand.ModifySellerDisabledCommand(sellerId);

        // interface -> application
         this.sellerApplication.modifySellerDisabled(command);

        // dto 만들기
        SellerDto.ModifySellerDisabled.Data data = new SellerDto.ModifySellerDisabled.Data(sellerId);

        // 응답 만들기
        return ApiResponse.success(data, "seller disabled");
    }
}
