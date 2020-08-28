package com.example.paging2

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("_embedded")val embedded: Embedded,
    @SerializedName("_links") val links: LinksX,
    val page: Page
)

data class Embedded(
    val itemList: List<Item>
)

data class LinksX(
    val first: First,
    val last: Last,
    val next: Next,
    val self: SelfX
)

data class Page(
    val number: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int
)

data class Item(
    val _links: Links,
    val acceptedQuantity: Any,
    val advance: Any,
    val availableSince: Any,
    val availabledDate: Any,
    val availabledWeight: Any,
    val categoryPath: Any,
    val createdDate: String,
    val customOrderImageCloudinaryId: Any,
    val customOrderImageID: Any,
    val daysToDelievery: Any,
    val deliveredOrCanceledDate: Any,
    val deliveredWeight: Any,
    val description: Any,
    val diameter: Any,
    val expectedDeliveryDate: String,
    val forwarded: Boolean,
    val grossWeight: Double,
    val gstBreakDown: List<Any>,
    val id: Int,
    val imageIds: List<ImageId>,
    val inventoryQuantity: Int,
    val itemNo: String,
    val itemStatus: String,
    val lastModified: String,
    val length: String,
    val manufactureCompany: String,
    val manufactureEmail: Any,
    val manufactureMobile: String,
    val manufactureName: String,
    val melting: Any,
    val name: String,
    val netWeight: Double,
    val payableAmount: Any,
    val paymentStatus: String,
    val processingDate: Any,
    val product: String,
    val productID: String,
    val productImage: String,
    val productImageCloudinaryId: String,
    val purity: Any,
    val quantity: Int,
    val quantityMeasure: Any,
    val rate: Any,
    val rateCutRequest: Any,
    val rejectForwarded: Boolean,
    val rejectionReason: Any,
    val resubmitedItemNumber: Any,
    val returnAction: Any,
    val returnCharge: Double,
    val returnNetAmount: Any,
    val returnQuantity: Any,
    val sampleWeight: Double,
    val seal: Any,
    val seller: String,
    val shipmentId: Any,
    val shipping: Boolean,
    val size: String,
    val sizeType: Any,
    val smsNotified: Boolean,
    val taxAmount: Any,
    val taxableAmount: Any,
    val totalWeight: Double,
    val unitDiscout: Any,
    val unitPrice: Any,
    val unitPriceBreakdown: List<Any>,
    val updatedDate: Any,
    val urgent: Boolean,
    val wholesalerEmail: String,
    val wholesalerName: String,
    val wholesalerNumber: String,
    val whosSample: Any,
    val whosalerCompany: String
)

data class Links(
    val forwarded: Forwarded,
    val order: Order,
    val productLink: ProductLink,
    val self: Self
)

data class ImageId(
    val cloudinaryImagePublicId: String,
    val id: Int,
    val imageId: Any
)

data class Forwarded(
    val href: String
)

data class Order(
    val href: String
)

data class ProductLink(
    val href: String
)

data class Self(
    val href: String
)

data class First(
    val href: String
)

data class Last(
    val href: String
)

data class Next(
    val href: String
)

data class SelfX(
    val href: String
)