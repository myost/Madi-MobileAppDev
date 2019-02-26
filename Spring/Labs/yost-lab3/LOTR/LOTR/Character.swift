//
//  Character.swift
//  LOTR
//
//  Created by Madi Yost on 2/24/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation

struct Character : Decodable {
    let name: String
    let description: String
    let image: String
    let race: String
    let status: String
}
