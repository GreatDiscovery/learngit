package main

import "fmt"

func main() {
	for i:=0; i < 10; i++ {
		//fmt.Println(i)
		//if i > 5 {
		//	break
		//}
		fmt.Printf("%v - %v -  %v \n", i , string(i), []byte(string(i)))
	}

	data := []float64{1, 2,3}
	for _, v := range data {
		v += 1
	}
}
