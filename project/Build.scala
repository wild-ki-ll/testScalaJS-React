import sbt._
import Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin
import scalajsbundler.sbtplugin.ScalaJSBundlerPlugin.autoImport._

object test {

  object Versions {
    val scala 			      = "2.11.8"
    val scalaJsDom 		    = "0.9.1"
    val htmlWebpackPlugin = "~2.26.0"
    val htmlLoader 		    = "~0.4.3"
    val react 			      = "~15.4.2"
    val scalaJsReact	    = "0.1.0-SNAPSHOT"
  }

  object Dependencies {

    lazy val library = Seq(
      "com.github.eldis"  %%%! "scalajs-react"  % Versions.scalaJsReact
    )

    lazy val npmDep = Seq(
      "react"               -> Versions.react,
      "react-dom"           -> Versions.react
    )

    lazy val npmDevDep = npmDep ++ Seq(
      "html-webpack-plugin" -> Versions.htmlWebpackPlugin,
      "html-loader"         -> Versions.htmlLoader
    )
  }


  object Settings {
    type PC = Project => Project

    def Prj(prjName: String): PC = { p: Project =>
      p.in(file("."))
        .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
        .settings(
          name := prjName,
          scalaVersion := Versions.scala,
          requiresDOM in Test := true,
          enableReloadWorkflow := false,
          libraryDependencies ++= Dependencies.library,
          npmDependencies     in Compile ++= Dependencies.npmDep,
          npmDevDependencies  in Compile ++= Dependencies.npmDevDep,
          webpackConfigFile in fastOptJS := Some(baseDirectory.value / "config" / "webpack.config.js"),
          webpackConfigFile in fullOptJS := Some(baseDirectory.value / "config" / "webpack.config.js")
        )
      }

  }

  object Projects {
    lazy val test = project.configure(Settings.Prj("testApp"))
  }

}
