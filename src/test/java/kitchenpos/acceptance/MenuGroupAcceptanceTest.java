package kitchenpos.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.domain.MenuGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("메뉴 그룹 관리")
public class MenuGroupAcceptanceTest extends AcceptanceTest {
    @DisplayName("메뉴 그룹을 관리한다")
    @Test
    void manage() {
        //when
        MenuGroup request = createRequest();
        ExtractableResponse<Response> createdResponse = 생성_요청(request);
        //then
        생성됨(createdResponse, request);
        //when
        ExtractableResponse<Response> selectedResponse = 조회_요청();
        //then
        조회됨(selectedResponse);
    }

    public static MenuGroup createRequest() {
        MenuGroup request = new MenuGroup();
        request.setName("추천메뉴");

        return request;
    }

    public static ExtractableResponse<Response> 생성_요청(MenuGroup request) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/menu-groups")
                .then().log().all()
                .extract();
    }

    public static void 생성됨(ExtractableResponse<Response> response, MenuGroup request) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        MenuGroup menuGroup = response.as(MenuGroup.class);
        assertThat(menuGroup.getName()).isEqualTo(request.getName());
    }

    public static ExtractableResponse<Response> 조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/menu-groups")
                .then().log().all()
                .extract();
    }

    public static void 조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<MenuGroup> menuGroups = Arrays.asList(response.as(MenuGroup[].class));
        assertThat(menuGroups.size()).isEqualTo(1);
    }
}
