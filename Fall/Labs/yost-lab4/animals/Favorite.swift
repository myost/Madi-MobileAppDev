//
//  Favorite.swift
//  animals
//
//  Created by Madi Yost on 10/9/18.
//  Copyright © 2018 Madi Yost. All rights reserved.
//

import Foundation

class Favorite : Codable {
    var favLandAnimal : String?
    var favWaterAnimal : String?
    
    init(){
        favLandAnimal = "Your favorite land animal"
        favWaterAnimal = "Your favorite sea animal"
    }
}
