package testApp

import scalajs.js
import js.JSApp
import org.scalajs.dom
import eldis.react._

object Main extends JSApp {

  def main(): Unit = {
    ReactDOM.render(
      TestReactComponent(),
      dom.document.getElementById("testReactComponent")
    )
  }

}
