package com.example.android3

import android.annotation.SuppressLint
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android3.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CustomRecyclerAdapter
    var users = ArrayList<Card>()
    var foundUsers = ArrayList<Card>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CustomRecyclerAdapter(this, object : LikeListener {
            override fun onPressedLike(user: Card) {
                Log.d("like", user.name)
                Snackbar.make(
                    findViewById(R.id.mainLayout),
                    "Нажат лайк: ${user.name}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewer.layoutManager = layoutManager

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        title = "Notifications"

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        toolbar.navigationIcon?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                colorFilter = BlendModeColorFilter(Color.BLACK, BlendMode.SRC_IN)
            } else {
                setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
            }
        }

        users = arrayListOf(
            Card(
                resources.getIdentifier("img_1", "drawable", packageName),
                "Исаак Ньютон",
                "4 января 1643 года — 31 марта 1727 года",
                "Мужчина",
                "Автор фундаментального труда «Математические начала натуральной философии», в котором он обнародовал законы движения и закон всемирного тяготения, сформировавшие господствующую научную точку зрения вплоть до появления общей теории относительности."
            ),
            Card(
                resources.getIdentifier("img_2", "drawable", packageName),
                "Иисус Христос",
                "1 год до н. э. — 33 год",
                "Мужчина",
                "В христианстве центральная личность и предсказанный в Ветхом Завете Мессия," +
                        " ставший искупительной жертвой за грехи людей. Основными источниками сведений о жизни" +
                        " и учении Иисуса Христа являются Евангелия и другие книги Нового Завета."
            ),
            Card(
                resources.getIdentifier("img_3", "drawable", packageName),
                "Будда Шакьямуни",
                "около 563 до н. э. — около 483 до н. э.",
                "Мужчина",
                "Духовный учитель, основатель буддизма, одной из трёх мировых религий." +
                        " Является ключевой фигурой в буддизме. Рассказы о его жизни, его изречения, диалоги" +
                        " с учениками и монастырские заветы были обобщены его последователями после его смерти и легли" +
                        " в основу канонических собраний буддийских текстов."
            ),
            Card(
                resources.getIdentifier("img_4", "drawable", packageName),
                "Конфуций",
                "около 551 до н. э. — 479 год до н. э.",
                "Мужчина",
                "Древний мыслитель и философ Китая. Его учение оказало глубокое" +
                        " влияние на жизнь Китая и Восточной Азии, став основой философской системы, известной как конфуцианство." +
                        " Конфуций основал первый университет и систематизировал летописи, составленные в разных княжествах."
            ),
            Card(
                resources.getIdentifier("img_5", "drawable", packageName),
                "Альберт Эйнштейн",
                "14 марта 1879 — 18 апреля 1955",
                "Мужчина",
                "Физик-теоретик, один из основателей современной теоретической физики." +
                        " Эйнштейн — автор более 300 научных работ по физике, а также около 150 книг и статей в области истории и" +
                        " философии науки, публицистики и других. Он разработал несколько монументальных физических теорий."
            ),
            Card(
                resources.getIdentifier("img_6", "drawable", packageName),
                "Галилео Галилей",
                "15 февраля 1564 — 8 января 1642",
                "Мужчина",
                "Итальянский физик, механик, астроном, философ, математик, оказавший значительное влияние на науку своего времени." +
                        " Он одним из первых использовал телескоп для наблюдения небесных тел и сделал ряд выдающихся астрономических открытий."
            ),
            Card(
                resources.getIdentifier("img_7", "drawable", packageName),
                "Аристотель",
                "384 до н. э. — 322 до н. э.",
                "Мужчина",
                "Греческий философ и эрудит классического периода в Древней Греции. Его труды охватывают многие предметы," +
                        " включая физику, биологию, зоологию, метафизику, логику, этику, эстетику, поэзию, театр, музыку, риторику, психологию," +
                        " лингвистику, экономику, политику, метеорологию, геологию и государственное управление. Аристотель представил сложный" +
                        " синтез различных философий, существовавших до него."
            ),
            Card(
                resources.getIdentifier("img_8", "drawable", packageName),
                "Чарльз Роберт Дарвин",
                "12 февраля 1809 — 19 апреля 1882",
                "Мужчина",
                "Английский натуралист и путешественник, одним из первых пришедший к выводу и обосновавший идею" +
                        " о том, что все виды живых организмов эволюционируют со временем и происходят от общих предков. В своей теории" +
                        " основным механизмом эволюции видов Дарвин назвал естественный отбор."
            ),
            Card(
                resources.getIdentifier("img_9", "drawable", packageName),
                "Джеймс Клерк Максвелл",
                "13 июня 1831 — 5 ноября 1879",
                "Мужчина",
                "Британский (шотландский) физик, математик и механик." +
                        " Максвелл заложил основы современной классической электродинамики (уравнения Максвелла)," +
                        " ввёл в физику понятия тока смещения и электромагнитного поля."
            ),
            Card(
                resources.getIdentifier("img_10", "drawable", packageName),
                "Карл Генрих Маркс",
                "5 мая 1818 — 14 марта 1883",
                "Мужчина",
                "Немецкий философ, социолог, экономист, писатель, поэт," +
                        " политический журналист, лингвист, общественный деятель, историк. Наиболее известными его трудами являются «Манифест" +
                        " Коммунистической партии» (в соавторстве с Фридрихом Энгельсом) и «Капитал. Критика политической экономии»."
            ),
        )
        foundUsers.addAll(users)

        binding.recyclerViewer.adapter = adapter
        adapter.setList(
            users
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_icon, menu)
        val item = menu?.findItem(R.id.search)

        if (item != null) {
            val searchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        users.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        foundUsers.forEach {
                            if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
                                users.add(it)
                            }
                        }
                        binding.recyclerViewer.adapter!!.notifyDataSetChanged()
                    } else {
                        users.clear()
                        users.addAll(foundUsers)
                        binding.recyclerViewer.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
        return true
    }
}