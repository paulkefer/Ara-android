/*
 * Copyright (c) 2019. Fulton Browne
 *  This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.andromeda.ara.feeds


import com.andromeda.ara.util.RssFeedModel
import com.yelp.fusion.client.connection.YelpFusionApiFactory
import java.io.IOException
import java.util.*


class Food {
    fun getFood(log: String, lat: String): ArrayList<RssFeedModel> {
        val rssFeedModel1: ArrayList<RssFeedModel> = ArrayList()
        val main = RssFeedModel("", "", "", "", "")
        val apiFactory = YelpFusionApiFactory()
        try {
            val params = HashMap<String, String>()
            val yelpFusionApi = apiFactory.createAPI("TysaYZNEZB4aK1JhLAMT0BCeG0sdxDffcoFmnFnsEcVN5U9d4YA3UeRnrrw0FovvCsmWIalZQwexcPAqecflXv51tAXEtctkOgrdD3CIUculH7ieskJc6fKTguo4XXYx")
            params["latitude"] = lat
            params["longitude"] = log
            params["categories"] = "food"

            val call = yelpFusionApi.getBusinessSearch(params)
            val response = call.execute()
            val count = response.body().total
            val count2 = 1
            var title = "err"
            var info: String = "err"
            var web: String = "err"
            var image: String = "err"
            var imageList: List<String>
            if (count2 <= count) {
                // rssFeedModel1.add(ArrayList)
                for (i in 0 until response.body().businesses.size) {
                    title = response.body().businesses[i].name
                    web = response.body().businesses[i].url
                    image = response.body().businesses[i].imageUrl
                    val stars = response.body().businesses[i].rating
                    val imageList = response.body().businesses[i].photos
                    val open = response.body().businesses[i].isClosed
                    val stars1 = "$stars stars"
                    if (open) {
                        info = stars1 + System.lineSeparator() + " closed now"
                    } else {
                        info = stars1 + System.lineSeparator() + " open now"
                    }

                    rssFeedModel1.add(RssFeedModel(info, web, title, image, ""))
                }
            } else {
                rssFeedModel1.add(RssFeedModel("err1", "err", "err", "err", ""))
            }


        } catch (e: IOException) {
            e.printStackTrace()
            rssFeedModel1.add(RssFeedModel("err", "err", "err", "err", ""))
        }


        // general params


        return rssFeedModel1
    }

}
