package contracts

import org.apache.http.HttpHeaders
import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType


Contract.make {
    description'Should return created status with the uuid in the Location header'
    request {
        method POST()
        url '/users'
        headers {
            header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        }
        body([
                "identityId": "115de516-ae8a-4db5-b63b-969c6696a848",
                "name"      : "Foundation",
                "email"     : "user@email.com",
                "country"   : "Spain",
                "role"      : "USER",
                "postalCode": "8798"
        ])
    }
    response {
        status CREATED()
        headers {
            header(HttpHeaders.LOCATION, $(producer(regex("/users/*."))))
        }
    }
}

