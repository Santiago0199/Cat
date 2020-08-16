package com.santiagoperdomo.cat.model

import com.santiagoperdomo.cat.util.Constants

class VoteRequest(image_id: String, sub_id: String) {

    var image_id: String? = null
    var sub_id: String? = null
    var value: Int = Constants.NUMBER_VOTE

    init {
        this.image_id = image_id
        this.sub_id = sub_id
    }

}