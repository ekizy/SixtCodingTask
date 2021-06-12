package com.ekiz.sixtcodingtask.data.uimodels

import com.google.gson.annotations.SerializedName

class CarUIModel (private val id: String? = null,
                  private val modelIdentifier: String? = null,
                  private val modelName: String? = null,
                  private val name: String? = null,
                  private val make: String? = null,
                  private val group: String? = null,
                  private val color: String? = null,
                  private val series: String? = null,
                  private val fuelType: String? = null,
                  private val fuelLevel: String? = null,
                  private val transmission: String? = null,
                  private val licensePlate: String? = null,
                  private val latitude: Double? = null,
                  private val longitude: Double? = null,
                  private val innerCleanliness: String? = null,
                  private val carImageUrl: String? = null)