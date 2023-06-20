package com.example.myapplication.model

data class SuperHeroes(val images: String, val title: String, val detail: String) {
    companion object {
        val heroList: MutableList<SuperHeroes> = mutableListOf(
            SuperHeroes(
                "https://i.annihil.us/u/prod/marvel/i/mg/9/b0/537bc2375dfb9.jpg",
                "Scarlet Witch",
                "Scarlet Witch"
            ),
            SuperHeroes(
                "https://i.annihil.us/u/prod/marvel/i/mg/c/60/55b6a28ef24fa.jpg",
                "Iron Man",
                "Iron Man"
            ),
            SuperHeroes(
                "https://i.annihil.us/u/prod/marvel/i/mg/9/00/537bcb1133fd7.jpg",
                "Wolverine",
                "Wolverine"
            ),
            SuperHeroes(
                "https://x.annihil.us/u/prod/marvel/i/mg/e/e0/537bafa34baa9.jpg",
                "Hulk",
                "Hulk"
            ),
            SuperHeroes(
                "https://x.annihil.us/u/prod/marvel/i/mg/c/b0/537bc5f8a8df0.jpg",
                "Storm",
                "Storm"
            ),
            SuperHeroes(
                "https://i.annihil.us/u/prod/marvel/i/mg/9/a0/537bc7f6d5d23.jpg",
                "Spider-Man",
                "Spider-Man"
            ),
            SuperHeroes(
                "https://i.annihil.us/u/prod/marvel/i/mg/9/03/537ba26276348.jpg",
                "Ultron",
                "Ultron"
            ),
            SuperHeroes(
                "http://x.annihil.us/u/prod/marvel/i/mg/9/80/537ba5b368b7d.jpg",
                "BlackPanther",
                "BlackPanther"
            ),
            SuperHeroes(
                "https://i.annihil.us/u/prod/marvel/i/mg/7/40/537bca868687c.jpg",
                "Captain America",
                "Captain America"
            ),
            SuperHeroes(
                "https://x.annihil.us/u/prod/marvel/i/mg/6/30/537ba61b764b4.jpg",
                "Winter Soldier",
                "Winter Soldier"
            ),
            SuperHeroes(
                "https://i.annihil.us/u/prod/marvel/i/mg/6/60/537bb1756cd26.jpg",
                "Iron Fist",
                "Iron Fist"
            ),
        )
    }
}
