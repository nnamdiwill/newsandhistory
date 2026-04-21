package com.example.newsandhistory.newsinfo.samples

import com.example.newsandhistory.dataclasses.CurrentNews

val sampleNews = listOf(

    CurrentNews(
        news = "https://www.welt.de/politik/deutschland/article251941728/Europawahl-2024-AfD-zweitstaerkste-Kraft-aber-in-Europa-droht-ihr-die-Bedeutungslosigkeit.html",
        author = "Frederik Schindler",
        country = "de",
        title = "Die AfD wird bei der Europawahl zweitstärkste Kraft in Deutschland, in Ostdeutschland liegt sie sogar vorn. Doch",
        description = "Die AfD wird bei der Europawahl zweitstärkste Kraft in Deutschland, in Ostdeutschland liegt sie sogar vorn. Doch ungeachtet dieses Erfolgs steuert sie im Europaparlament in eine ungewisse Zukunft: Die Fraktionslosigkeit droht. Damit gingen mehrere Nachteile einher.",
        isUpdated = true
    ),

    CurrentNews(
        news= "https://www.deccanchronicle.com/southern-states/hyderabad-police-rescue-24-babies-in-interstate-human-trafficking-investigation-898167",
        author = "DC Correspondent",
        country = "us",
        title = "Hyderabad: Police Rescue 24 Babies in Interstate Human Trafficking Investigation",
        description = "Hyderabad: Police Rescue 24 Babies in Interstate Human Trafficking Investigation",
        isUpdated = true
    ),
    CurrentNews(
        news="https://www.dvidshub.net/image/8462257/nws-yorktown-promotion-ceremony-missile-park",
        author = "Max Lonzanida",
        country = "us",
        title="Yorkton",
        description = "Yorktown, Va. (June 7, 2024) BM2 Aleksander Kaczinski stands with Capt. Dan Patrick, Commanding Officer, Naval Weapons Station Yorktown after a promotion ceremony held at the installation’s missile park. Kaczinski is assigned to the installation’s Port Operations Department. (U.S. Navy Photo by Max Lonzanida/Released).",
        isUpdated = true
    )
)

val oneSampleNews = CurrentNews(
    news= "https://www.dvidshub.net/image/8462259/nws-yorktown-promotion-ceremony-missile-park",
    author = "Max Lonzanida",
    country = "us",
    title = "",
    description = "Yorktown, Va. (June 7, 2024) MA2 Hannah Strickland stands with Capt. Dan Patrick, Commanding Officer, Naval Weapons Station Yorktown after a promotion ceremony held at the installation’s missile park. Strickland is assigned to the installation’s Security Department. (U.S. Navy Photo by Max Lonzanida/Released).",
    isUpdated = true
)