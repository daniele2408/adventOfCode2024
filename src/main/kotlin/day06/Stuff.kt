package org.example.day06

import org.example.model.Grid

interface Stuff {
    companion object {
        fun from(input: String) : Stuff {
            return when (input) {
                "#", "O" -> Obstacle
//                "^" -> Guard(Grid.Direction.N)
//                ">" -> Guard(Grid.Direction.E)
//                "v" -> Guard(Grid.Direction.S)
//                "<" -> Guard(Grid.Direction.W)
                ".", "^" -> Empty
                else -> throw RuntimeException("value $input not supported")
            }
        }
    }
}