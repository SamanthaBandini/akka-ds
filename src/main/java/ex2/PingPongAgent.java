package ex2;

/*****************************************************************
 JADE - Java Agent DEvelopment Framework is a framework to develop 
 multi-agent systems in compliance with the FIPA specifications.
 Copyright (C) 2000 CSELT S.p.A.

 GNU Lesser General Public License

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation, 
 version 2.1 of the License.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the
 Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 Boston, MA  02111-1307, USA.
 *****************************************************************/

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Adapted from Stefano Mariani's PingPongAgent in
 * ds-jade\src\ds\lab\jade\messaging\PingPongAgent.java.
 * A simple agent showing basic communication functionalities
 * showed within Distributed System lab 2016/2017.
 *
 * @author samantha.bandini3@studio.unibo.it
 * @author annagiulia.leoni@studio.unibo.it
 */
public class PingPongAgent extends Agent {

    /*
     * Our custom behaviour responding either to 'ping' or 'pong' messages.
     */
    private class PingPongBehaviour extends CyclicBehaviour {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        /*
         * We need these fields to properly manage both messages and their
         * replies.
         */
        private final String content;
        private final MessageTemplate msgTemplate;

        public PingPongBehaviour(final String c, final MessageTemplate t) {
            this.content = c;
            this.msgTemplate = t;
        }

        @Override
        public void action() {
            /*
             * Asynchronous, selective receive.
             */
            final ACLMessage msg = this.myAgent.receive(this.msgTemplate);
            if (msg != null) {
                PingPongAgent.this.log("Received message '" + msg.getContent()
                        + "' from agent <" + msg.getSender() + ">.");
                /*
                 * We create the reply...
                 */
                final ACLMessage reply = msg.createReply();
                /*
                 * ...then we set required fields...
                 */
                reply.setPerformative(ACLMessage.PROPOSE);
                if ("ping".equals(this.content)) {
                    reply.setContent("pong");
                } else {
                    reply.setContent("ping");
                }
                /*
                 * ...and finally we send the message.
                 */
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.myAgent.send(reply);
            } else {
                /*
                 * If a template-compliant message was not received, block this
                 * behaviour until next message.
                 */
                this.block();
            }
        }

    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /*
     * Templates used by the agent to properly distinguish different incoming
     * messages (the first is used solely to build the other two).
     */
    private final MessageTemplate template = MessageTemplate.and(
            MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
            MessageTemplate.MatchOntology("ping-pong"));

    private final MessageTemplate pingTemplate = MessageTemplate.and(
            this.template, MessageTemplate.MatchContent("ping"));
    private final MessageTemplate pongTemplate = MessageTemplate.and(
            this.template, MessageTemplate.MatchContent("pong"));

    private void log(final String msg) {
        System.out.println("[" + this.getAID() + "]: " + msg);
    }

    @Override
    protected void setup() {
        this.log("Started.");
        /*
         * One behaviour to reply to 'ping'...
         */
        this.addBehaviour(new PingPongBehaviour("ping", this.pingTemplate));
        /*
         * ...and one behaviour to reply to 'pong'.
         */
        this.addBehaviour(new PingPongBehaviour("pong", this.pongTemplate));
    }

}
