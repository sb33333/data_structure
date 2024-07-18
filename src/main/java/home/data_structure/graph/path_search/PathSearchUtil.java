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
    private final SearchGoal<T> searchGoal;
    private final SearchConditionConfigurer<T> conditionConfigurer;
    private final PathFilterConfigurer<T> pathFilterConfigurer;

    /**
     * Private constructor to initialize the PathSearchUtil with a graph and start/end vertices.
     *
     * @param g The graph in which to search for paths.
     * @param s The starting vertex for the path search.
     * @param e The ending vertex for the path search.
     */
    private PathSearchUtil(Graph<T> g, Vertex<T> s, Vertex<T> e) {
        this.g = g;
        this.s = s;
        this.e = e;
        
        this.conditionConfigurer = new SearchConditionConfigurer<>(this, e);
        this.completeCondition = this.conditionConfigurer.alwaysTrue;
        this.abortCondition = conditionConfigurer.terminateWhenFindTargetVertex;
        
        this.pathFilterConfigurer = new PathFilterConfigurer<>(this);
        this.filter = pathFilterConfigurer.PROHIBIT_SAME_EDGE;
    
        this.searchGoal = (vertex, goal) -> goal.equals(vertex);
    }
    
    /**
     * Static factory method to create an instance of PathSearchUtil.
     *
     * @param g The graph in which to search for paths.
     * @param s The starting vertex for the path search.
     * @param e The ending vertex for the path search.
     * @return A new instance of PathSearchUtil.
     */
    public static <T> PathSearchUtil<T> create(Graph<T> g, Vertex<T> s, Vertex<T> e) {
        return new PathSearchUtil<T>(g, s, e);
    }

    /**
     * Executes the path search algorithm.
     *
     * @return A set of paths from the start vertex to the end vertex. Returns null if an exception occurs.
     */
    public Set<Path<T>> search() {
        System.out.println("search()");
        try {
            return PathSearch.findPathDFS(this.g, this.s, this.e, this.searchGoal, this.filter, this.completeCondition, this.abortCondition).call();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

     /**
     * Provides a way to configure search conditions.
     *
     * @return An instance of SearchConditionConfigurer.
     */
    public SearchConditionConfigurer<T> condition() {
        return this.conditionConfigurer;
    }

     /**
     * Provides a way to configure path filters.
     *
     * @return An instance of PathFilterConfigurer.
     */
    public PathFilterConfigurer<T> pathFilter() {
        return this.pathFilterConfigurer;
    }
    
    
    public static class SearchConditionConfigurer<T> {
        private final PathSearchUtil<T> parent;
        private final Predicate<Path<T>> alwaysTrue = (t) -> true;
        private final Predicate<Path<T>> terminateWhenFindTargetVertex;

        /**
         * Private constructor to initialize the SearchConditionConfigurer.
         *
         * @param parent The parent PathSearchUtil instance.
         * @param end The ending vertex for the path search.
         */
        private SearchConditionConfigurer (PathSearchUtil<T> parent, Vertex<T> end) {
            this.parent = parent;
            this.terminateWhenFindTargetVertex = (path) -> path.getVisitedVertices().contains(end);
            
        }
        
        /**
         * Sets the condition for completing the search.
         *
         * @param condition The condition to be met for the search to complete.
         * @return The current instance of SearchConditionConfigurer.
         */
        public SearchConditionConfigurer<T> setCompleteCondition(Predicate<Path<T>> condition) {
            this.parent.completeCondition = condition;
            return this;
        }

         /**
         * Sets the condition for aborting the search.
         *
         * @param condition The condition to be met for the search to abort.
         * @return The current instance of SearchConditionConfigurer.
         */
        public SearchConditionConfigurer<T> setAbortCondition (Predicate<Path<T>> condition) {
            this.parent.abortCondition = condition;
            return this;
        }

        /**
         * Resets the complete condition to always true.
         *
         * @return The current instance of SearchConditionConfigurer.
         */
        public SearchConditionConfigurer<T> resetCompleteCondition() {
            this.parent.completeCondition = alwaysTrue;
            return this;
        }

        /**
         * Sets a minimum length for the path to be considered complete.
         *
         * @param minLength The minimum length of the path.
         * @return The current instance of SearchConditionConfigurer.
         */
        public SearchConditionConfigurer<T> minLength(int minLength) {
            this.parent.completeCondition = path -> path.getLength() >= minLength;
            return this;
        }

         /**
         * Sets a maximum length for the path to be considered abort.
         *
         * @param maxLength The maximum length of the path.
         * @return The current instance of SearchConditionConfigurer.
         */
        public SearchConditionConfigurer<T> maxLength(int maxLength) {
            this.parent.abortCondition = path -> path.getLength() > maxLength;
            return this;
        }

         /**
         * Returns to the parent PathSearchUtil instance.
         *
         * @return The parent PathSearchUtil instance.
         */
        public PathSearchUtil<T> and() {
            return this.parent;
        }
    }
    
    
    
    public static class PathFilterConfigurer<T> {
        private final PathSearchUtil<T> parent;

        /**
         * Private constructor to initialize the PathFilterConfigurer.
         *
         * @param parent The parent PathSearchUtil instance.
         */
        private PathFilterConfigurer(PathSearchUtil<T> parent) {
            this.parent = parent;
        }
        
        /**
         * The same search path (with identical edges and vertices) is prohibited.
         */
        private final PathFilterCondition<T> PROHIBIT_SAME_SEARCH_HISTORY = (path, edge, start)->{
            List<Path<T>> subPaths = new ArrayList<>();
            Vertex<T> pathStart = path.getStart();
            path.getVisitedEdges().stream().reduce(
                pathStart,
                (vertex, visitedEdge) -> {
                    subPaths.add(new PathImpl<>(vertex, List.of(visitedEdge)));
                    return vertex;
                },
                (before, after) -> after
            );
            return subPaths.stream().filter(subPath -> (start == subPath.getStart() && edge == subPath.getVisitedEdges().get(0))).findAny().isEmpty();
        };
        
        /**
         * The same search edge is prohibited.
         */
        private final PathFilterCondition<T> PROHIBIT_SAME_EDGE = (path, edge, start) -> !path.getVisitedEdges().contains(edge);
    

        /**
         * Sets a custom path filter condition.
         *
         * @param filter The custom path filter condition.
         * @return The current instance of PathFilterConfigurer.
         */
        public PathFilterConfigurer<T> filter(PathFilterCondition<T> filter) {
            this.parent. filter= filter;
            return this;
            
        }

         /**
         * Prohibits the same search history in the path.
         *
         * @return The current instance of PathFilterConfigurer.
         */
        public PathFilterConfigurer<T> prohibitSameSearchHistory() {
            this.parent.filter =PROHIBIT_SAME_SEARCH_HISTORY;
            return this;
        }


        /**
         * Prohibits the same edge in the path.
         *
         * @return The current instance of PathFilterConfigurer.
         */
        public PathFilterConfigurer<T> prohibitSameEdge() {
            this.parent.filter = PROHIBIT_SAME_EDGE;
            return this;
        }

         /**
         * Returns to the parent PathSearchUtil instance.
         *
         * @return The parent PathSearchUtil instance.
         */
        public PathSearchUtil<T> and() {
            return this.parent;
        }
    }
}