package com.ashwin.examples.cachemanagerdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.*
import com.ashwin.libraries.cachemanager.CacheManager
import com.ashwin.libraries.cachemanager.Store
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Try out the following CacheManager APIs
         */

        //save()

        //saveStringResponse()

        //saveJsonObjectResponse()

        //saveJsonArrayResponse()

        //saveBitmap()

        //list()

        //delete()

        //clear()
    }

    fun save() {
        // save string using CacheManager
        CacheManager.save(applicationContext, Store.FILE, "test.txt", "WooHoo! this is cached text")

        // retrieve cached string from CacheManager
        var content = CacheManager.get(applicationContext, Store.FILE, "test.txt")

        if (content == null) {
            content = "Error"
        }
        textView.setText(content)
    }

    fun saveStringResponse() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://www.google.com"
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // save string response
                    CacheManager.save(applicationContext, Store.FILE, "response.txt", response)

                    // get cached response
                    val res = CacheManager.get(applicationContext, Store.FILE, "response.txt")
                    textView.text = "Response is: ${res?.substring(0, 500)}"
                },
                Response.ErrorListener {
                    textView.text = "That didn't work!"
                }
        )
        queue.add(stringRequest)
    }

    fun saveJsonObjectResponse() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://gist.githubusercontent.com/ashwindmk/7fc3da64a5aab125794cbd5e5b870add/raw/30016ce1496b666bbbaabf8f33b52fd1f43ad684/employees.json"
        val jsonRequest = JsonObjectRequest(url, null,
                Response.Listener { response ->
                    try {
                        // save json object
                        CacheManager.save(applicationContext, Store.FILE, "json_response.txt", response)

                        // get cached json object
                        val res = CacheManager.getJsonObject(applicationContext, Store.FILE, "json_response.txt")

                        textView.text = res.toString()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { error -> VolleyLog.e("Error: ", error.message)
        })
        queue.add(jsonRequest)
    }

    fun saveJsonArrayResponse() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://gist.githubusercontent.com/ashwindmk/6d2fc5633a248166b327a0d4b2758b38/raw/28c1de54e3d7305ef3cdddddee4348096ad5c676/employees_array.json"
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    try {
                        // save json array
                        CacheManager.save(applicationContext, Store.FILE, "json_response.txt", response)

                        // get cached json array
                        val res = CacheManager.getJsonArray(applicationContext, Store.FILE, "json_response.txt")

                        textView.text = res.toString()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener {
                    textView.text = "Error"
                }
        )

        queue.add(jsonArrayRequest)
    }

    fun saveBitmap() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://firebasestorage.googleapis.com/v0/b/json-response-holder.appspot.com/o/movies%2Fcaptain_america_winter_soldier.jpg?alt=media&token=0af54da7-aaf0-4324-93d7-ce56a2b66aee"
        val imageRequest = ImageRequest(url,
                Response.Listener { response ->
                    // save bitmap to CacheManager
                    CacheManager.save(applicationContext, Store.FILE, "bitmap_image.png", response)

                    // retrieve the bitmap from CacheManager
                    val cachedBitmap = CacheManager.getBitmap(applicationContext, Store.FILE, "bitmap_image.png")

                    imageView.setImageBitmap(cachedBitmap)
                }, 2048, 2048, null,
                Response.ErrorListener {
                    textView.text = "Error loading image"
                }
        )

        queue.add(imageRequest)
    }

    fun list() {
        // get list of files in a directory
        val list: List<String> = CacheManager.list(applicationContext, Store.FILE)

        var stringBuilder = StringBuilder("")
        for (filename in list) {
            stringBuilder.append("\n").append(filename)
        }
        textView.text = stringBuilder.toString()
    }

    fun delete() {
        // delete a cached file
        CacheManager.delete(applicationContext, Store.FILE, "test.txt")
    }

    fun clear() {
        // delete all cached files in specified directory
        CacheManager.delete(applicationContext, Store.FILE)
    }

}
