# LSort
LSort is a hybrid sorting algorithm.

## :warning: WARNING

This repository is intentionally created with NO LICENSE. The [elucidation](https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/licensing-a-repository) below is posted by GitHub:

> However, without a license, the default copyright laws apply, meaning that you retain all rights to your source code and no one may reproduce, distribute, or create derivative works from your work.

## Introduction

LSort is a hybrid sorting algorithm using [MSD radix sort](https://en.wikipedia.org/wiki/Radix_sort#Most_significant_digit,_forward_recursive) and [Merge-insertion sort](https://en.wikipedia.org/wiki/Merge-insertion_sort). On relatively small arrays, LSort fall back to Insertion sort. For larger ones, it first uses MSD radix sort to scale down the subarrays, then turn to Merge-insertion sort.

The specific implementation in this repository is concurrent.

## Performance

LSort uses MSD radix sort to reduce the number of recursions. This allows it to have average runtime $\displaystyle \Theta \left( N + \frac1K \log N \right)$ and worst-case runtime $O \left( N^2 \right)$. 

<details>
  <summary>Performance of the Java implementation in this repository: </summary>
  
  ### Environment
  * CPU: AMD Ryzen 7 7840HS w/ Radeon 780M Graphics (16) @ 3.800GHz
  * OpenJDK: 20.0.2
</details>

![Screenshot_20230906_182622](https://github.com/YJChen22/LSort/assets/144348310/8fc42811-2ed6-43b7-9a7f-acba83314ed5)


