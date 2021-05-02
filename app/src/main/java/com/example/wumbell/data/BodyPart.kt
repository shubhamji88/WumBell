package com.example.wumbell.data

data class Equipment(
    val fieldName:String,
    val fieldCount:Int
)

data class BodyPart (
        val id:Int,
    val name: String,
    val list:ArrayList<Equipment>)
class ExerciseData{
    companion object{
        val data= mutableListOf(BodyPart(0,"Chest", arrayListOf(Equipment("Kettlebell",20), Equipment("Jumping Jacks",25),Equipment("Kettlebell",20), Equipment("Bench press",25))),
            BodyPart(1,"Chest", arrayListOf(Equipment("Treadmill",20), Equipment("Bench press",25),Equipment("Kettlebell",20), Equipment("Jumping Jacks",25))),
            BodyPart(2,"Shoulder", arrayListOf(Equipment("Kettlebell",20), Equipment("Speed bag",25),Equipment("Bench press",20), Equipment("Jumping Jacks",25))),
            BodyPart(3,"Shoulder", arrayListOf(Equipment("Bench press",20), Equipment("Jumping Jacks",25),Equipment("Kettlebell",20), Equipment("Speed bag",25))))
        val equipmentList= arrayOf("Select Equipment","Dumbell","Kettlebell","Bench press","Declined bench press","Cable crossover","Indoor rower")
        val bodypartList= arrayOf("Select Body Part","Chest", "shoulder","bicep","tricep","upper back","lower back", "hamstring","glutes","calves")
    }
}