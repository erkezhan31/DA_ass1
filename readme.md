# Assignment 1

## Алгоритмы
- MergeSort: divide & conquer, buffer reuse, cutoff
- QuickSort: randomized pivot, smaller-first recursion
- Deterministic Select: Median-of-Medians
- Closest Pair: O(n log n) D&C

## Метрики
- Comparisons
- Swaps
- Allocations
- Recursion depth

## Рекуррентные соотношения
- MergeSort: T(n) = 2T(n/2) + Θ(n) → Θ(n log n)
- QuickSort: T(n) ≈ 2T(n/2) + Θ(n) (ср.) → Θ(n log n)
- Select: T(n) = T(n/5) + T(7n/10) + Θ(n) → Θ(n)
- Closest Pair: T(n) = 2T(n/2) + Θ(n) → Θ(n log n)

## Отчёт
- Построить графики (time vs n, depth vs n)
- Обсудить влияние кэша / GC
- Сравнить теорию и практику

## Git Workflow
- main: стабильные версии
- feature/*: для каждого алгоритма
- Чистая история коммитов (см. задание)
