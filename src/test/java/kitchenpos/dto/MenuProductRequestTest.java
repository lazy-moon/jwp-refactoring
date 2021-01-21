package kitchenpos.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

@DisplayName("메뉴 제품 요청")
public class MenuProductRequestTest extends ValidateBase {
    @DisplayName("수량이 1보다 작은 경우")
    @Test
    public void minimumQuantity() {
        MenuProductRequest request = new MenuProductRequest(1L, 0);

        validate(request);
    }
}
