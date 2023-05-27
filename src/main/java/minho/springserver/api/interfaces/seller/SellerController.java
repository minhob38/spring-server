package minho.springserver.api.interfaces.seller;

import lombok.RequiredArgsConstructor;
import minho.springserver.api.application.seller.SellerApplication;
import minho.springserver.api.domain.board.BoardInfo;
import minho.springserver.api.domain.board.BoardQuery;
import minho.springserver.api.domain.seller.SellerInfo;
import minho.springserver.api.domain.seller.input.SellerCommand;
import minho.springserver.api.domain.seller.input.SellerQuery;
import minho.springserver.api.interfaces.board.BoardDto;
import minho.springserver.exception.BadRequestException;
import minho.springserver.exception.BoardException;
import minho.springserver.response.ApiResponse;
import minho.springserver.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/{sellerId}")
    public ApiResponse<SellerDto.ReadSeller.Data> getSeller(@PathVariable("sellerId") Long sellerId) throws BadRequestException {
        // query 만들기
        SellerQuery.ReadSellerQuery query = SellerQuery.ReadSellerQuery
                .builder()
                .sellerId(sellerId)
                .build();

        // interface -> application
        SellerInfo.Seller seller = this.sellerApplication.readSeller(query);

        // dto 만들기
        SellerDto.ReadSeller.Data data = new SellerDto.ReadSeller.Data(seller);

        // 응답 만들기
        return ApiResponse.success(data);
    }

}
