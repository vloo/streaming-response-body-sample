import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody

@RestController
class SampleController {

    @RequestMapping("/streaming" ,method = [GET])
    fun data(): ResponseEntity<StreamingResponseBody> {
        return ResponseEntity(
            StreamingResponseBody { out ->
                out.write("some data".toByteArray())
            }, HttpStatus.OK)
    }

    @RequestMapping("/string" ,method = [GET])
    fun stringData(): ResponseEntity<String> {
        return ResponseEntity("string-data", HttpStatus.OK)
    }


}