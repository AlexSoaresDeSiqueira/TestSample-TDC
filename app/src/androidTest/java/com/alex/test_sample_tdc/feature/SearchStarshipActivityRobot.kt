package com.alex.test_sample_tdc.feature

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alex.test_sample_tdc.R
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer


fun SearchStarshipActivityTest.setupStarshipActivity(
    block: SearchStarshipActivityRobot.() -> Unit
) = SearchStarshipActivityRobot().apply(block)

class SearchStarshipActivityRobot {

    fun mockDeathStarship(serverRule: MockWebServer) {
        serverRule.enqueue(MockResponse().setBody("{\n" +
                "    \"count\": 1,\n" +
                "    \"next\": null,\n" +
                "    \"previous\": null,\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"name\": \"Death Star\",\n" +
                "            \"model\": \"DS-1 Orbital Battle Station\",\n" +
                "            \"manufacturer\": \"Imperial Department of Military Research, Sienar Fleet Systems\",\n" +
                "            \"cost_in_credits\": \"1000000000000\",\n" +
                "            \"length\": \"120000\",\n" +
                "            \"max_atmosphering_speed\": \"n/a\",\n" +
                "            \"crew\": \"342953\",\n" +
                "            \"passengers\": \"843342\",\n" +
                "            \"cargo_capacity\": \"1000000000000\",\n" +
                "            \"consumables\": \"3 years\",\n" +
                "            \"hyperdrive_rating\": \"4.0\",\n" +
                "            \"MGLT\": \"10\",\n" +
                "            \"starship_class\": \"Deep Space Mobile Battlestation\",\n" +
                "            \"pilots\": [],\n" +
                "            \"films\": [\n" +
                "                \"https://swapi.co/api/films/1/\"\n" +
                "            ],\n" +
                "            \"created\": \"2014-12-10T16:36:50.509000Z\",\n" +
                "            \"edited\": \"2014-12-22T17:35:44.452589Z\",\n" +
                "            \"url\": \"https://swapi.co/api/starships/9/\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"))

    }

    infix fun launch(block: SearchStarshipActionRobot.() -> Unit) =
        SearchStarshipActionRobot().apply(block)
}

class SearchStarshipActionRobot {

    fun searchStarship(starshipName: String) {
        onView(withId(R.id.editTextNave))
            .perform(typeText(starshipName))

        closeSoftKeyboard()

        onView(withId(R.id.buttonSearch))
            .perform(click())
    }

    infix fun check(block: SearchStarshipCheckRobot.() -> Unit) =
        SearchStarshipCheckRobot().apply(block)
}

class SearchStarshipCheckRobot {

    fun starshipDetails(
        name: String,
        model: String,
        starshipClass: String
    ) {
        onView(withId(R.id.textViewName))
            .check(matches(withText(name)))

        onView(withId(R.id.textViewModel))
            .check(matches(withText(model)))

        onView(withId(R.id.textViewStarshipClass))
            .check(matches(withText(starshipClass)))
    }
}