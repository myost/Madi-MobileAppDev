//
//  Film.swift
//  Star Wars Films
//
//  Created by Madi Yost on 3/3/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation

struct Film: Decodable {
    let title: String
    let episode_id: Int
    let opening_crawl: String
    let director: String
    let producer: String
}

struct FilmData: Decodable {
    var results = [Film]()
}
