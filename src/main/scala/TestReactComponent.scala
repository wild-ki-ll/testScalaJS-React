/**
 * Created by kirill on 06.02.2017.
 */

package testApp

import eldis.react._
import vdom._
import prefix_<^._

import scala.scalajs.js.annotation.ScalaJSDefined

@ScalaJSDefined
class TestReactComponent extends Component[Nothing]("TestReactComponent") {
  case class State(text: String)

  override def initialState = {
    State(text = "test1")
  }

  override def render() = {
    val text = state.text

    <.div()(
      <.input(
        ^.placeholder := "input some text, please",
        ^.onChange ==> handlerOnChange _,
        ^.autoFocus := true,
        ^.value := text
      )(),
      <.label()(text)
    )
  }

  def handlerOnChange(e: ReactKeyboardEventI): Unit = {
    setState(state.copy(text = e.target.value))
  }
}

@ScalaJSDefined
object TestReactComponent extends TestReactComponent