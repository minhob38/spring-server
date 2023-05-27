package minho.springserver.api.interfaces.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.application.seller.SellerApplication;
import minho.springserver.api.domain.seller.input.SellerCommand;
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
    public ApiResponse<SellerDto.CreateSeller.Data> patchPost(@Validated @RequestBody SellerDto.CreateSeller.RequestBody requestBody) {
        // command 만들기
        SellerCommand.CreateSellerCommand command = SellerCommand.CreateSellerCommand
                .builder()
                .sellerName(requestBody.getSellerName())
                .businessNumber(requestBody.getBusinessNumber())
                .email(requestBody.getEmail())
                .build();

        // interface -> application
        Long createdId = this.sellerApplication.createSeller(command);

        // dto 만들기
        SellerDto.CreateSeller.Data data = new SellerDto.CreateSeller.Data(createdId);

        // 응답 만들기
        return ApiResponse.success(data, "seller created");
    }
}
