import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus.OK
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@WebMvcTest(SampleController::class)
@ContextConfiguration(classes = [SampleController::class])

internal class SampleControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var controller: SampleController

    @WithMockUser("my-user")
    @Test
    @RepeatedTest(100)
    fun `StreamingResponseBody response in combination with spring security sometimes fail`() {
        // when
        val response = mockMvc.perform(MockMvcRequestBuilders.get("/streaming")).andReturn().response

        // then
        assertThat(response.status).isEqualTo(OK.value())
    }

    @WithMockUser("my-user")
    @Test
    @RepeatedTest(100)
    fun `string response in combination with WithMockUser is ok`() {

        // when
        val response = mockMvc.perform(MockMvcRequestBuilders.get("/string")).andReturn().response

        // then
        assertThat(response.status).isEqualTo(OK.value())
    }
}