# data_structure

그래프 자료구조 및 경로 탐색 구현.

## 주요 인터페이스

1. Graph
   * 정점(Vertex)과 간선(Edge)의 집합으로 이루어진 자료구조.
   * 방향 그래프 vs 무방향 그래프
     * 간선의 탐색 방향(단방향, 양방향)
   * 가중 그래프 vs 비가중 그래프
     * 간선에 가중치(weight)가 있거나 없는 그래프.
2. Vertex(Node)
   * 그래프를 구성하는 기본 단위.
3. Edge
   * 두 정점 간의 관계.
4. Path
   * 정점을 연결하는 간선의 집합.

## 사용
### PathSearcher
한 정점에서 다른 정점으로 가는 간선의 집합(경로, Path)를 찾습니다.
검색 시 아래 사항을 지정해야 합니다. 
* 완료 조건(completeCondition): 경로가 만족해야 하는 조건을 지정합니다.
  * 예: 경로의 최소 길이, 모든 정점을 방문해야 한다, 모든 간선을 방문해야 한다 등
* 탐색 중지 조건(abortCondition): 더 이상 탐색을 진행하지 않을 조건을 지정합니다.
  * 예: 최대 탐색 길이
* 간선 조건(filter): 간선이 경로에 포함될 조건을 검사합니다.
  * 예: 같은 간선은 경로에 포함될 수 없음


PathSearcher.create
* 검색을 위한 객체를 생성합니다.

PathSearcher.condition
* 완료 조건과 탐색 중지 조건을 설정합니다.

PathSearcher.filter
* 간선 조건을 설정합니다.

PathSearcher.search
* 경로 탐색을 실시합니다.

## 테스트케이스
<h3><a href="https://github.com/sb33333/data_structure/blob/71d32c44adce3363654415986d2a2d558a90ce5d/src/test/java/home/data_structure/path_search/PathSearcherTest.java">test class</a></h3>
<a href="https://namu.wiki/w/%EC%BE%A8%EB%8B%88%ED%9E%88%EC%8A%A4%EB%B2%A0%EB%A5%B4%ED%81%AC%20%EB%8B%A4%EB%A6%AC%20%EA%B1%B4%EB%84%88%EA%B8%B0%20%EB%AC%B8%EC%A0%9C">쾨니히스베르크 다리 건너기 문제</a>


## 생각해보기
* 그래프를 추상 자료형으로 변환, 반대로 변환(행렬, 연결 리스트)
* 트리 자료구조 구현체 만들어보기
* 노드 확장 기능 만들기(expand)
* 맹목적 탐색, 경험적 탐색
* vertex의 getValue는 변경될 수 있음.
* graph 인터페이스 구조
  * graph interface 아래에 unweightedGraph, weightedGraph
  * weightedGraph interface와 unweightedGraph interface를 분리
