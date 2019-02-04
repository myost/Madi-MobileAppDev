//
//  WorldDestinations.swift
//  lab1_tabs
//
//  Created by Madi Yost on 2/1/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation

struct WorldDestinations: Decodable {
    let continent: String
    let cities: [String]
}
