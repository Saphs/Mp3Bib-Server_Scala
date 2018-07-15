
import org.scalatest.{MustMatchers, WordSpec}
import test_implementations.{Constants, FileAccessTestImpl}

class FileAccessSpec extends WordSpec with MustMatchers{
  val resPath: String = Constants.resourcePath
  val fileAccessor: FileAccessTestImpl = new FileAccessTestImpl

  "FileAcess" should {
    "provide access to a Mp3File" when {
      "given a valid path" in {
        val mp3File = fileAccessor.readMp3(s"${resPath}example.mp3")
        mp3File.isDefined must be (true)
      }
    }
    "manipulate correctly" when {
      "renaming an existing file" in {
        val oldName = s"${resPath}example.mp3"
        val newName = s"${resPath}exampleChanged.mp3"
        fileAccessor.renameExistingFile(oldName, newName) must be(true)
        fileAccessor.renameExistingFile(newName, oldName) must be(true)
      }
      "saving a valid mp3 to disk" in {
        val path = s"${resPath}example.mp3"
        val mp3File = fileAccessor.readMp3(path)
        mp3File.exists(fileAccessor.saveFile(s"${resPath}exampleSave.mp3", _)) must be(true)
      }
      "deleting an existing mp3 from disk" in {
        fileAccessor.deleteFile(s"${resPath}exampleSave.mp3") must be(true)
      }
      "overriding existing files" in {
        val testPath = s"${resPath}example.mp3"
        val mp3File = fileAccessor.readMp3(testPath)
        val overrideResult = mp3File.exists(fileAccessor.overrideMp3(testPath, _))
        overrideResult must be(true)
      }
    }
  }
}


