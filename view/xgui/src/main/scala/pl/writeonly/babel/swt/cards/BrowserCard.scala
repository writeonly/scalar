package pl.writeonly.babel.swt.cards

import org.eclipse.swt.browser.Browser
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.widgets.Text

import pl.writeonly.xscalawt.XScalaWT.Assignments._
import pl.writeonly.xscalawt.XScalaWT.browser
import pl.writeonly.xscalawt.XScalaWT.button
import pl.writeonly.xscalawt.XScalaWT.composite
import pl.writeonly.xscalawt.XScalaWT.onSelectionImplicit
import pl.writeonly.xscalawt.XScalaWT.string2setText
import pl.writeonly.xscalawt.XScalaWT.tabItem
import pl.writeonly.xscalawt.XScalaWT.text
import com.weiglewilczek.slf4s.Logging

import pl.writeonly.babel.swt.faces.BrowserFace
import pl.writeonly.scala.swt.layout.BorderData
import pl.writeonly.xscalawt.YScalaWT.borderLayoutScalars
import pl.writeonly.xscalawt.YScalaWT.sashHorizontal
import pl.writeonly.xscalawt.YScalaWT.sashVerdical
import pl.writeonly.xscalawt.YScalaWT.textArea
import pl.writeonly.xscalawt.YScalaWT.textToString

object BrowserCard extends Logging {

  def run(console: Text, none: Browser, editor: Text) {
    try {
      console.setText(none.evaluate(editor.getText).toString)
    }
    catch {
      case e: Exception => console.setText(e + "\n" + e.getStackTraceString)
    }
  }

  def apply(browserFace : BrowserFace) = {
    var none: Browser = null
    var url: Text = null
    var editor: Text = null
    var console: Text = null
    var evaluated: AnyRef = null

    composite(
      tabItem("Browser"),
      borderLayoutScalars(),
      composite(
        borderLayoutScalars(),
        layoutData = BorderData.NORTH,
        text(url = _, "http://en.lernu.net/cgi-bin/vortaro.pl"),
        button(
          "Connect",
          layoutData = BorderData.WEST,
          { e: SelectionEvent => none.setUrl(url) }),
        button(
          "Run",
          layoutData = BorderData.EAST,
          { e: SelectionEvent => BrowserCard.run(console,none,editor)}
        )
      ),
      sashVerdical(
        sashHorizontal(
          browser(
            none = _
          ),
          textArea(editor = _, "return document.getElementById('uzantovortaro') != null")
        ),
        textArea(console = _, "")
      )
    )

  }
}