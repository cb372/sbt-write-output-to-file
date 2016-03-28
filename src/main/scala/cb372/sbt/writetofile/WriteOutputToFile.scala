package cb372.sbt.writetofile

import sbt._
import sbt.Keys._

object WriteOutputToFile extends AutoPlugin {

  object autoImport {
    lazy val writeOutputToFile_outputFile = settingKey[File]("Where to save the process's output")
    lazy val writeOutputToFile_append = settingKey[Boolean]("Whether to keep appending output to the file every time the process runs")
  }

  import autoImport._

  override def projectSettings = Seq(
    writeOutputToFile_outputFile := file("output.log"),
    writeOutputToFile_append := false,

    fork in (Compile,run) := true,
    outputStrategy in (Compile,run) := Some(new CustomOutput(new ReusableFileOutputStream(
      writeOutputToFile_outputFile.value, 
      writeOutputToFile_append.value
    )))
  )

}
