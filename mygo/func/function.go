package main

import "fmt"

func visit(numbers []int, callback func(int)) {
	for _, n := range numbers {
		callback(n)
	}
}

func main() {
	x := 0
	increment := func() int {
		x++
		return x
	}

	fmt.Println(1, increment())
	//fmt.Println(2, increment())
	visit([]int{1, 2, 3, 4}, func(n int) {
		fmt.Println(n)
	})
}
