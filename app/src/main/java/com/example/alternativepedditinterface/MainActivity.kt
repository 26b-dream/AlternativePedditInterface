package com.example.alternativepedditinterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.alternativepedditinterface.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val myWebView = findViewById<WebView>(R.id.theWebView)
        myWebView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String?) {
                view.settings.javaScriptEnabled = true
                view.evaluateJavascript("""link = document.createElement('link');
link.rel = 'stylesheet';
link.type = 'text/css';
link.href = 'https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css';
document.head.appendChild(link);

html = "";
document.querySelectorAll('div.link:not(.promoted)').forEach(function(linkDiv) {
    // Find url with class title in first_link
    title = linkDiv.querySelector('a.title').outerHTML;
    upvote = linkDiv.querySelector('div.arrow.up').outerHTML;
    downvote = linkDiv.querySelector('div.arrow.down').outerHTML;
    score = linkDiv.querySelector("div.score").innerText;
    subreddit = linkDiv.querySelector("a.subreddit").outerHTML
    author = linkDiv.querySelector("a.author").outerHTML
    domain = linkDiv.querySelector("span.domain").outerHTML
    time = linkDiv.querySelector("time.live-timestamp").outerHTML
    comments = linkDiv.querySelector("a.comments").outerHTML
    const imgElement = linkDiv.querySelector("img");
    if (imgElement) {
        img = linkDiv.querySelector("img").outerHTML
    } else {
        img = ""
    }


    html += `
<div class="card">
<div class="row">
<div class="col-2">${'$'}{upvote}<br><p class="text-center">${'$'}{score}</p><br>${'$'}{downvote}</div>
<div class="col-4"><h5 class="card-title">${'$'}{title}</h5>

  <small class="text-body-secondary">to ${'$'}{subreddit} by ${'$'}{author}<br>(${'$'}{domain})<br>${'$'}{time}<br>${'$'}{comments}</small>
  <p class="card-text">
  </p>
</div>
<div class="col-4">${'$'}{img}</div>
</div>
</div>
`
});



// Replace body content with html
document.body.innerHTML = html;
console.log(html)
// Clear head

document.head.innerHTML += `<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
<title>Bootstrap Example</title>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>`
""", null)
            }

        }
        myWebView.loadUrl("https://old.reddit.com")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}