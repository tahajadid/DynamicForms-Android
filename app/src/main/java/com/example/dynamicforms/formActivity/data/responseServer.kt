package com.example.dynamicforms.formActivity.data // ktlint-disable filename

data class responseServer(
    var merchants: ArrayList<Merchants> = arrayListOf(),
)

data class Merchants(
    var id: Int? = null,
    var code: String? = null,
    var names: Names? = Names(),
    var billPayment: Boolean? = null,
    var topUp: Boolean? = null,
    var internetMerchant: Boolean? = null,
    var merchantServices: ArrayList<MerchantServices> = arrayListOf(),
    var logo: String? = null,
    var hubCode: String? = null,
    var hold: Boolean? = null,
    var isFavorite: Boolean? = null,
)

data class MerchantServices(
    var id: Int? = null,
    var code: String? = null,
    var billPayment: Boolean? = null,
    var topUp: Boolean? = null,
    var names: Names? = Names(),
    var merchantServiceFieldsGroup: String? = null,
    var merchantServiceFields: ArrayList<MerchantServiceFields> = arrayListOf(),
    var merchantServiceType: MerchantServiceType? = MerchantServiceType(),
    var currency: Currency? = Currency(),
    var onlineService: Boolean? = null,
    var merchantServiceDescription: MerchantServiceDescription? = MerchantServiceDescription(),
    var feesSettings: FeesSettings? = FeesSettings(),
    var amountFloor: Int? = null,
    var amountCeiling: Int? = null,
    var providesItems: Boolean? = null,
    var merchantServiceDisplayCategory: MerchantServiceDisplayCategory? = MerchantServiceDisplayCategory(),
)

data class MerchantServiceFields(
    var code: String? = null,
    var names: Names? = Names(),
    var dataType: String? = null,
    var minLength: Int? = null,
    var maxLength: Int? = null,
    var required: Boolean? = null,
    var merchantServiceFieldListOfValues: String? = null,
    var rank: Int? = null,
    var hidden: Boolean? = null,
    var pattern: String? = null,
    var placeholder: Placeholder? = Placeholder(),
)

data class MerchantServiceType(
    var code: String? = null,
    var name: String? = null,
)

data class MerchantServiceDescription(
    var dflt: String? = null,
    var ar: String? = null,
    var fr: String? = null,
    var en: String? = null,
    var lo: String? = null,
    var km: String? = null,
)

data class MerchantServiceDisplayCategory(
    var dflt: String? = null,
    var ar: String? = null,
    var fr: String? = null,
    var en: String? = null,
    var lo: String? = null,
    var km: String? = null,
)

data class Names(
    var dflt: String? = null,
    var ar: String? = null,
    var fr: String? = null,
    var en: String? = null,
    var lo: String? = null,
    var km: String? = null,
)

data class Placeholder(
    var dflt: String? = null,
    var ar: String? = null,
    var fr: String? = null,
    var en: String? = null,
    var lo: String? = null,
    var km: String? = null,
)

data class Currency(
    var id: Int? = null,
)

data class FeesSettings(
    var percentage: Int? = null,
    var flat: Int? = null,
    var ceiling: Int? = null,
    var floor: Int? = null,
)
