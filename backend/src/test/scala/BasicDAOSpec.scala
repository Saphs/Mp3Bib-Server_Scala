import org.scalatest.{MustMatchers, WordSpec}
import test_implementations.BasicDAOTestImpl

class BasicDAOSpec extends WordSpec with MustMatchers {

  "BasicDAO" should {
    "provide a sequence of paths for known mp3 files" in {
      val dao = new BasicDAOTestImpl
      dao.diskLocations must be (Seq("C:\\Users\\Tiz\\IdeaProjects\\Eidos\\backend\\src\\test\\resources\\directory_test_structure\\example.mp3"))
    }
  }
}
