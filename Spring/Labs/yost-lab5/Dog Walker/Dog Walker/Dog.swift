//
//  Dog.swift
//  Dog Walker
//
//  Created by Madi Yost on 3/13/19.
//  Copyright © 2019 Madi Yost. All rights reserved.
//

import Foundation

struct Dog {
    var id: String
    var name: String
    var breed: String
    var size: String
    var owner: String
    
    init(id: String, name: String, breed: String, size: String, owner: String){
        self.id = id
        self.name = name
        self.breed = breed
        self.size = size
        self.owner = owner
    }
}
