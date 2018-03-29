name := "sbt-write-output-to-file"
organization := "cb372"

sbtPlugin := true

publishMavenStyle := false
bintrayOrganization := Some("cb372")
bintrayRepository := "sbt-plugins"

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

import ReleaseTransformations._
lazy val releaseSteps = Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  releaseStepCommandAndRemaining("^ test"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  releaseStepCommandAndRemaining("^ publish"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
releaseCrossBuild := false
releaseProcess := releaseSteps
crossSbtVersions := Vector("0.13.16", "1.1.1")
