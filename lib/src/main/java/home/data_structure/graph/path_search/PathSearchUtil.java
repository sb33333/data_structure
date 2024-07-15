package home.data_structure.graph.path_search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import home.data_structure.graph.Graph;
import home.data_structure.graph.Path;
import home.data_structure.graph.PathImpl;
import home.data_structure.graph.Vertex;

public class PathSearchUtil<T> {
    private final Graph<T> g;
    private final Vertex<T> s;
    private final Vertex<T> e;
    private PathFilterCondition<T> filter;
    private Predicate<Path<T>> completeCondition;
    private Predicate<Path<T>> abortCondition;
    private SearchGoal<T> searchGoal;
    private final SearchConditionConfigurer<T> conditionConfigurer;
    private final PathFilterConfigurer<T> pathFilterConfigurer;

    private PathSearchUtil(Graph<T> g, Vertex<T> s, Vertex<T> e) {
        this.g = g;
        this.s = s;
        this.e = e;
        
        this.conditionConfigurer = new SearchConditionConfigurer<T>(this, e);
        this.completeCondition = this.conditionConfigurer.alwaysTrue;
        this.abortCondition = conditionConfigurer.terminateWhenFindTargetVertex;
        
        this.pathFilterConfigurer = new PathFilterConfigurer<T>(this);
        this.filter = pathFilterConfigurer.PROHIBIT_SAME_EDGE;
    
        this.searchGoal = (vertex, goal) -> goal.equals(vertex);
    }
    
    public static <T> PathSearchUtil<T> create(Graph<T> g, Vertex<T> s, Vertex<T> e) {
        return new PathSearchUtil<T>(g, s, e);
    }
    public Set<Path<T>> search() {
        System.out.println("search()");
        try {
            return PathSearch.findPath(this.g, this.s, this.e, this.searchGoal, this.filter, this.completeCondition, this.abortCondition).call();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public SearchConditionConfigurer<T> condition() {
        return this.conditionConfigurer;
    }
    public PathFilterConfigurer<T> pathFilter() {
        return this.pathFilterConfigurer;
    }
    
    
    public static class SearchConditionConfigurer<T> {
        private final PathSearchUtil<T> parent;
        private final Predicate<Path<T>> alwaysTrue = (t) -> true;
        private final Predicate<Path<T>> terminateWhenFindTargetVertex;

        private SearchConditionConfigurer (PathSearchUtil<T> parent, Vertex<T> end) {
            this.parent = parent;
            this.terminateWhenFindTargetVertex = (path) -> path.getVisitedVertices().contains(end);
            
        }
        
        public SearchConditionConfigurer<T> setCompleteCondition(Predicate<Path<T>> condition) {
            this.parent.abortCondition = condition;
            return this;
        }
        public SearchConditionConfigurer<T> setAbortCondition (Predicate<Path<T>> condition) {
            this.parent.abortCondition = condition;
            return this;
        }
        public SearchConditionConfigurer<T> resetCompleteCondition() {
            this.parent.completeCondition = alwaysTrue;
            return this;
        }
        public SearchConditionConfigurer<T> minLength(int minLength) {
            this.parent.completeCondition = path -> path.getLength() >= minLength;
            return this;
        }
        public SearchConditionConfigurer<T> maxLength(int maxLength) {
            this.parent.abortCondition = path -> path.getLength() > maxLength;
            return this;
        }
        public PathSearchUtil<T> and() {
            return this.parent;
        }
    }
    
    
    
    public static class PathFilterConfigurer<T> {
        private PathSearchUtil<T> parent;
        private PathFilterConfigurer(PathSearchUtil<T> parent) {
            this.parent = parent;
        }
        
        // The same search path(with identical edge and vertices) is prohibited.
        private final PathFilterCondition<T> PROHIBIT_SAME_SEARCH_HISTORY = (path, edge, start)->{
            List<Path<T>> subPaths = new ArrayList<>();
            Vertex<T> pathStart = path.getStart();
            path.getVisitedEdges().stream().reduce(
                pathStart,
                (vertex, visitedEdge) -> {
                    subPaths.add(new PathImpl<T>(vertex, List.of(visitedEdge)));
                    return vertex;
                },
                (before, after) -> after
            );
            return subPaths.stream().filter(subPath -> {
                return (start == subPath.getStart() && edge == subPath.getVisitedEdges().get(0));
            }).findAny().isEmpty();
        };
        
        // The same search edge is prohibited.
        private final PathFilterCondition<T> PROHIBIT_SAME_EDGE = (path, edge, start) -> path.getVisitedEdges().stream().filter(visited -> edge == visited).findAny().isEmpty();
    
        public PathFilterConfigurer<T> filter(PathFilterCondition<T> filter) {
            this.parent. filter= filter;
            return this;
            
        }
        public PathFilterConfigurer<T> prohibitSameSearchHistory() {
            this.parent.filter =PROHIBIT_SAME_SEARCH_HISTORY;
            return this;
        }

        public PathFilterConfigurer<T> prohibitSameEdge() {
            this.parent.filter = PROHIBIT_SAME_EDGE;
            return this;
        }
        public PathSearchUtil<T> and() {
            return this.parent;
        }
    }
}