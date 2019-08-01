package gr.uom.gnp.retrofitexample


import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    var customAdapter : CustomAdapter? = null
    var recyclerView : RecyclerView? = null
    var progressDialog : ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Loading. . .")
        progressDialog!!.show()

        val service : GetDataService? = RetrofitClientInstance.getRetrofitInstance()!!.create(GetDataService::class.java)
        val call : Call<List<RetroPhoto>> = service!!.getAllPhotos()
        call!!.enqueue(object : Callback<List<RetroPhoto>> {

            override fun onFailure(call: Call<List<RetroPhoto>>, t: Throwable) {
                progressDialog!!.dismiss()
                Toast.makeText(applicationContext,"Something went wrong...Please try later",Toast.LENGTH_SHORT)

            }

            override fun onResponse(call: Call<List<RetroPhoto>>, response: Response<List<RetroPhoto>>) {
                progressDialog!!.dismiss();
                response.body()?.let { generateDataList(it) }
            }

        })

    }
    private fun generateDataList(photoList:List<RetroPhoto>){
        recyclerView=findViewById(R.id.customRecycler)
        var customAdapter = CustomAdapter(this)
        customAdapter.setPhotos(photoList)
        val viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.customRecycler).apply{
            layoutManager = viewManager
            adapter = customAdapter
        }

    }
}
