
import com.twitter.util.Future
import scala.sys.process._

object MongoStartup extends{

  def startOnStaticPath(): Unit  ={
    val cmd = Seq("C:\\Program Files\\MongoDB\\Server\\3.4\\bin\\mongod")
    println(s"Command to be run: $cmd")

    val process = Process(cmd)
    val io = BasicIO.standard(false)

   Future( process.run(io) )
  }
}

