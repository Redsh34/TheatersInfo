package com.alg.minfo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="timings")
public class ShowEntity {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    public MovieEntity getMentity() {
        return Mentity;
    }

    public void setMentity(MovieEntity mentity) {
        Mentity = mentity;
    }

    @ManyToOne
    @JoinColumn(name ="movie_id")
    private MovieEntity Mentity;

    public theatreEntity getTentity() {
        return Tentity;
    }

    public void setTentity(theatreEntity tentity) {
        Tentity = tentity;
    }

    @ManyToOne
    @JoinColumn(name ="theatre_id")
    private theatreEntity Tentity;

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    private String showtime;
}
